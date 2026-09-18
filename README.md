# README.md Demo
This is a demo README.md for RSR Robotics, with a demo robot "Cookie".

# Robot Overview
Cookie is a robot designed by FTC Team #67676 for the 2026-2027 FTC season BioBuzz. Cookie features a double-sided motor-driven intake, a 4-nectar wide catapult, a mecanum drivetrain, a LimeLight 3A for vision, and four color sensors to detect nectar. The CAD is available at <put link here>

# Controls
Cookie uses 2 buttons and 2 joysticks to be controlled. A driver controls movement and turning with the left and right joysticks respectively, controls the catapult with "x", and automatically aims at the flower with "a". Cookie automatically determines when the intake should be on/off using input from the four color sensors. 

# Subsystem-A: Intake
Cookie's double-sided motor intake is controlled with two GoBilda 5000 Series motors and can be found in Intake.java. The four color sensors monitor if a catapult "channel" contains a ball, and automatically controls the intake to prevent overpossesion and excessive battery usage.

# Subsystem-B: Catapult
The 4-nectar wide catapult mechanism is controlled in catapult.java. It utilizes two Gobilda 5000 Series motors with REV ThroughBore encoders to quickly flick the catapult, leading to accurate shooting. The catapult can be controlled by direct driver input (x) or through autonomous command sequences.

# Subsystem-C: Drivetrain
A 4-motor mecanum chassis is controlled in drive.java, and utilizes four Gobilda 5000 Series motors for fast movement. It utilizes input from the onboard REV Hub IMU for field-centric navigation, allowing for accurate omnidirectional movement across the field.

# Subsystem-D: Vision Processing
Cookie's vision pipeline is managed in vision.java. The LimeLight 3A tracks flower AprilTags to calculate offset angle for automated target alignment.

# Autonomous Routines
To effectively navigate across the field, Cookie uses PedroPathing 3.0 for accurate trajectory calcuation.

Routine 1: Scores preload pollen, intakes an extra set of 4 nectar, shoots in the other flower, and parks. <br>
Routine 2: Navigates around center field obstacles to score preload pollen and park. 

# Photos
You can put any photos of your robot CAD, real-life photos, or code here. Please add a description under each image, so people know what they're looking at.

# Team Info
FTC Team: #67676 - Sigma Robots <br>
Season: 2026–2027 BioBuzz <br>
License: MIT License (I just have this here, it's not too important for a small repository)
