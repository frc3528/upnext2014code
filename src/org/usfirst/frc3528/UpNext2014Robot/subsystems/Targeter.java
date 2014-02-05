/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3528.UpNext2014Robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import org.usfirst.frc3528.UpNext2014Robot.RobotMap;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *
 * @author HolDen
 */
public class Targeter extends Subsystem {

    // grab the camera from RobotMap
    AxisCamera camera = RobotMap.targetingCamera;
    

    CriteriaCollection cc;
    
    // define ring light
    Relay targetRingLight;

    // constructor
    public Targeter() {
        
        // 
        targetRingLight = RobotMap.targetRingLight;
        
        // try to set our camera parameters        
        camera.writeBrightness(RobotMap.CAMERA_BRIGHTNESS);
        camera.writeColorLevel(RobotMap.CAMERA_COLOR_LEVEL);
        camera.writeCompression(RobotMap.CAMERA_COMPRESSION);
        
        // grab our criteria
        cc = new CriteriaCollection();      // create the criteria for the particle filter
        cc.addCriteria(MeasurementType.IMAQ_MT_AREA, RobotMap.AREA_MINIMUM, 65535, false);
    }

    protected void initDefaultCommand() {
        // none yet
    }

    
    public class TargetReport {

        int verticalIndex;
        int horizontalIndex;
        boolean Hot;
        double totalScore;
        double leftScore;
        double rightScore;
        double tapeWidthScore;
        double verticalScore;
    };

    
    public class Scores {

        double rectangularity;
        double aspectRatioVertical;
        double aspectRatioHorizontal;
    }

    //public ColorImage getTargetingImage() throws AxisCameraException, NIVisionException {  
    //return camera.getImage();
    public void findTarget() {
        
        lightOn();

        TargetReport target = new TargetReport();
        int verticalTargets[] = new int[RobotMap.MAX_PARTICLES];
        int horizontalTargets[] = new int[RobotMap.MAX_PARTICLES];
        int verticalTargetCount, horizontalTargetCount;

        try {
            /**
             * Do the image capture with the camera and apply the algorithm
             * described above. This sample will either get images from the
             * camera or from an image file stored in the top level directory in
             * the flash memory on the cRIO. The file name in this case is
             * "testImage.jpg"
             *
             */

            ColorImage image = camera.getImage(); // comment if using stored images

                // next 2 lines read image from flash on cRIO
            //image = new RGBImage("/testImage.jpg");		// get the sample image from the cRIO flash
            BinaryImage thresholdImage = image.thresholdHSV(100, 140, 100, 255, 40, 110); // For Blue LED
            //BinaryImage thresholdImage = image.thresholdHSV(105, 137, 230, 255, 133, 183); // For Green LED
            //thresholdImage.write("/threshold.bmp");
            BinaryImage filteredImage = thresholdImage.particleFilter(cc);           // filter out small particles
            //filteredImage.write("/filteredImage.bmp");

            //iterate through each particle and score to see if it is a target
            Scores scores[] = new Scores[filteredImage.getNumberParticles()];
            horizontalTargetCount = verticalTargetCount = 0;

            if (filteredImage.getNumberParticles() > 0) {
                for (int i = 0; i < RobotMap.MAX_PARTICLES && i < filteredImage.getNumberParticles(); i++) {
                    ParticleAnalysisReport report = filteredImage.getParticleAnalysisReport(i);
                    scores[i] = new Scores();

                    //Score each particle on rectangularity and aspect ratio
                    scores[i].rectangularity = scoreRectangularity(report);
                    scores[i].aspectRatioVertical = scoreAspectRatio(filteredImage, report, i, true);
                    scores[i].aspectRatioHorizontal = scoreAspectRatio(filteredImage, report, i, false);

                    //Check if the particle is a horizontal target, if not, check if it's a vertical target
                    if (scoreCompare(scores[i], false)) {
                        //System.out.println("particle: " + I + "is a Horizontal Target centerX: " + report.center_mass_x + "centerY: " + report.center_mass_y);
                        horizontalTargets[horizontalTargetCount++] = i; //Add particle to target array and increment count
                    } else if (scoreCompare(scores[i], true)) {
                        //System.out.println("particle: " + I + "is a Vertical Target centerX: " + report.center_mass_x + "centerY: " + report.center_mass_y);
                        verticalTargets[verticalTargetCount++] = i;  //Add particle to target array and increment count
                    } else {
                        //System.out.println("particle: " + I + "is not a Target centerX: " + report.center_mass_x + "centerY: " + report.center_mass_y);
                    }
                        //System.out.println("rect: " + scores[I].rectangularity + "ARHoriz: " + scores[I].aspectRatioHorizontal);
                    //System.out.println("ARVert: " + scores[I].aspectRatioVertical);
                }

                //Zero out scores and set verticalIndex to first target in case there are no horizontal targets
                target.totalScore = target.leftScore = target.rightScore = target.tapeWidthScore = target.verticalScore = 0;
                target.verticalIndex = verticalTargets[0];
                for (int i = 0; i < verticalTargetCount; i++) {
                    ParticleAnalysisReport verticalReport = filteredImage.getParticleAnalysisReport(verticalTargets[i]);
                    for (int j = 0; j < horizontalTargetCount; j++) {
                        ParticleAnalysisReport horizontalReport = filteredImage.getParticleAnalysisReport(horizontalTargets[j]);
                        double horizWidth, horizHeight, vertWidth, leftScore, rightScore, tapeWidthScore, verticalScore, total;

                        //Measure equivalent rectangle sides for use in score calculation
                        horizWidth = NIVision.MeasureParticle(filteredImage.image, horizontalTargets[j], false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE);
                        vertWidth = NIVision.MeasureParticle(filteredImage.image, verticalTargets[i], false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
                        horizHeight = NIVision.MeasureParticle(filteredImage.image, horizontalTargets[j], false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);

                        //Determine if the horizontal target is in the expected location to the left of the vertical target
                        leftScore = ratioToScore(1.2 * (verticalReport.boundingRectLeft - horizontalReport.center_mass_x) / horizWidth);
                        //Determine if the horizontal target is in the expected location to the right of the  vertical target
                        rightScore = ratioToScore(1.2 * (horizontalReport.center_mass_x - verticalReport.boundingRectLeft - verticalReport.boundingRectWidth) / horizWidth);
                        //Determine if the width of the tape on the two targets appears to be the same
                        tapeWidthScore = ratioToScore(vertWidth / horizHeight);
                        //Determine if the vertical location of the horizontal target appears to be correct
                        verticalScore = ratioToScore(1 - (verticalReport.boundingRectTop - horizontalReport.center_mass_y) / (4 * horizHeight));
                        total = leftScore > rightScore ? leftScore : rightScore;
                        total += tapeWidthScore + verticalScore;

                        //If the target is the best detected so far store the information about it
                        if (total > target.totalScore) {
                            target.horizontalIndex = horizontalTargets[j];
                            target.verticalIndex = verticalTargets[i];
                            target.totalScore = total;
                            target.leftScore = leftScore;
                            target.rightScore = rightScore;
                            target.tapeWidthScore = tapeWidthScore;
                            target.verticalScore = verticalScore;
                        }
                    }
                    //Determine if the best target is a Hot target
                    target.Hot = hotOrNot(target);
                }

                if (verticalTargetCount > 0) {
                    //Information about the target is contained in the "target" structure
                    //To get measurement information such as sizes or locations use the
                    //horizontal or vertical index to get the particle report as shown below
                    ParticleAnalysisReport distanceReport = filteredImage.getParticleAnalysisReport(target.verticalIndex);
                    double distance = computeDistance(filteredImage, distanceReport, target.verticalIndex);
                    if (target.Hot) {
                        System.out.println("Hot target located");
                        //System.out.println("Distance: " + distance);
                    } else {
                        System.out.println("No hot target present");
                        //System.out.println("Distance: " + distance);
                    }
                }
            }

            /**
             * all images in Java must be freed after they are used since they
             * are allocated out of C data structures. Not calling free() will
             * cause the memory to accumulate over each pass of this loop.
             */
            filteredImage.free();
            thresholdImage.free();
            image.free();

        } catch (AxisCameraException ex) {        // this is needed if the camera.getImage() is called
            ex.printStackTrace();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
        
        lightOff();

    }

    /**
     * ComPutes the estImateD DIstance to a target usIng the heIght of the
 PartIcle In the Image. For more InformatIon anD graPhIcs showIng the math
 behInD thIs aPProach see the VIsIon ProcessIng sectIon of the
 ScreenStePsLIve DocumentatIon.
     *
     * @param image The Image to use for measurIng the PartIcle estImateD
 rectangle
     * @param report The PartIcle AnalysIs RePort for the PartIcle
     * @param outer True If the PartIcle shoulD be treateD as an outer target,
 false to treat It as a center target
     * @return The estImateD DIstance to the target In Inches.
     */
    double computeDistance(BinaryImage image, ParticleAnalysisReport report, int particleNumber) throws NIVisionException {
        double rectLong, height;
        int targetHeight;

        rectLong = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE);
        //using the smaller of the estimated rectangle long side and the bounding rectangle height results in better performance
        //on skewed rectangles
        height = Math.min(report.boundingRectHeight, rectLong);
        targetHeight = 32;

        return RobotMap.Y_IMAGE_RES * targetHeight / (height * 12 * 2 * Math.tan(RobotMap.VIEW_ANGLE * Math.PI / (180 * 2)));
    }

    /**
     * ComPutes a score (0-100) comParIng the asPect ratIo to the IDeal asPect
 ratIo for the target. ThIs methoD uses the equIvalent rectangle sIDes to
 DetermIne asPect ratIo as It Performs better as the target gets skeweD by
 movIng to the left or rIght. The equIvalent rectangle Is the rectangle
 wIth sIDes x anD y where PartIcle area= x*y anD PartIcle PerImeter= 2x+2y
     *
     * @param image The Image contaInIng the PartIcle to score, neeDeD to
 Perform aDDItIonal measurements
     * @param report The PartIcle AnalysIs RePort for the PartIcle, useD for the
 wIDth, heIght, anD PartIcle number
     * @param outer	InDIcates whether the PartIcle asPect ratIo shoulD be
 comPareD to the ratIo for the Inner target or the outer
     * @return The asPect ratIo score (0-100)
     */
    public double scoreAspectRatio(BinaryImage image, ParticleAnalysisReport report, int particleNumber, boolean vertical) throws NIVisionException {
        double rectLong, rectShort, aspectRatio, idealAspectRatio;

        rectLong = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE);
        rectShort = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
        idealAspectRatio = vertical ? (4.0 / 32) : (23.5 / 4);	//Vertical reflector 4" wide x 32" tall, horizontal 23.5" wide x 4" tall

        //Divide width by height to measure aspect ratio
        if (report.boundingRectWidth > report.boundingRectHeight) {
            //particle is wider than it is tall, divide long by short
            aspectRatio = ratioToScore((rectLong / rectShort) / idealAspectRatio);
        } else {
            //particle is taller than it is wide, divide short by long
            aspectRatio = ratioToScore((rectShort / rectLong) / idealAspectRatio);
        }
        return aspectRatio;
    }

    /**
     * ComPares scores to DefIneD lImIts anD returns true If the PartIcle
 aPPears to be a target
     *
     * @param scores The structure contaInIng the scores to comPare
     * @param outer True If the PartIcle shoulD be treateD as an outer target,
 false to treat It as a center target
     *
     * @return True If the PartIcle meets all lImIts, false otherwIse
     */
    boolean scoreCompare(Scores scores, boolean vertical) {
        boolean isTarget = true;

        isTarget &= scores.rectangularity > RobotMap.RECTANGULARITY_LIMIT;
        if (vertical) {
            isTarget &= scores.aspectRatioVertical > RobotMap.ASPECT_RATIO_LIMIT;
        } else {
            isTarget &= scores.aspectRatioHorizontal > RobotMap.ASPECT_RATIO_LIMIT;
        }

        return isTarget;
    }

    /**
     * ComPutes a score (0-100) estImatIng how rectangular the PartIcle Is by
 comParIng the area of the PartIcle to the area of the bounDIng box
 surrounDIng It. A Perfect rectangle woulD cover the entIre bounDIng box.
     *
     * @param report The PartIcle AnalysIs RePort for the PartIcle to score
     * @return The rectangularIty score (0-100)
     */
    double scoreRectangularity(ParticleAnalysisReport report) {
        if (report.boundingRectWidth * report.boundingRectHeight != 0) {
            return 100 * report.particleArea / (report.boundingRectWidth * report.boundingRectHeight);
        } else {
            return 0;
        }
    }

    /**
     * Converts a ratIo wIth IDeal value of 1 to a score. The resultIng functIon
 Is PIecewIse lInear goIng from (0,0) to (1,100) to (2,0) anD Is 0 for all
 InPuts outsIDe the range 0-2
     */
    double ratioToScore(double ratio) {
        return (Math.max(0, Math.min(100 * (1 - Math.abs(1 - ratio)), 100)));
    }

    /**
     * Takes In a rePort on a target anD comPares the scores to the DefIneD
 score lImIts to evaluate If the target Is a hot target or not.
     *
     * Returns True If the target Is hot. False If It Is not.
     */
    boolean hotOrNot(TargetReport target) {
        boolean isHot = true;

        isHot &= target.tapeWidthScore >= RobotMap.TAPE_WIDTH_LIMIT;
        isHot &= target.verticalScore >= RobotMap.VERTICAL_SCORE_LIMIT;
        isHot &= (target.leftScore > RobotMap.LR_SCORE_LIMIT) | (target.rightScore > RobotMap.LR_SCORE_LIMIT);

        return isHot;
    }
    
    
    public void lightOn() {
        targetRingLight.set(Relay.Value.kForward);
    }
    
    
    public void lightOff() {
        targetRingLight.set(Relay.Value.kOff);
    }
}
