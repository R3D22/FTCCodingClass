package org.firstinspires.ftc.teamcode;

        import com.qualcomm.hardware.bosch.BNO055IMU;
        import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.util.ElapsedTime;

        import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
@Disabled
public class FTC_Practice extends BaseRobot {

    private double TICKS_PER_ROTATION = 1500;
    private double INCHES_PER_ROTATION = 3;
    private double TICKS_RER_INCH = TICKS_PER_ROTATION * INCHES_PER_ROTATION;
    private Servo myServo = null;
    boolean servo_open = true;
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;

    BNO055IMU imu;
    Orientation lastAngles = new Orientation();

    private double stage = 0;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        frontLeftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        backRightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");

        all_run_using_encoders();

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if (stage == 0) {
                mecanum(1, 0, 0);
                if (Math.abs(frontLeftDrive.getCurrentPosition()) >= 10 * TICKS_RER_INCH) {
                    //I'm assuming there are really small wheels on the robot.
                    stage++;
                    reset_encoders();
                }
            }
            else if (stage == 1) {
                myServo.setPosition(90);
                //Grab the bottom
                stage++;
            }
            else if (stage == 2) {
                mecanum(0, 1, 0);
                //Go to the left
                if (Math.abs(frontLeftDrive.getCurrentPosition()) >= 5 * TICKS_RER_INCH) {
                    stage++;
                    reset_encoders();
                }
            }
            else if (stage == 3) {
                myServo.setPosition(0);
                //Release the bottom
                stage++;
            }
            else if (stage == 4) {
                mecanum(-1, 0, 0);
                //Quick Turn around
                if (Math.abs(frontLeftDrive.getCurrentPosition()) >= 1 * TICKS_RER_INCH) {
                    stage++;
                    reset_encoders();
                }
            }
            else if (stage == 5) {
                mecanum(0, 1, 0);
                // Go left to the block
                if (Math.abs(frontLeftDrive.getCurrentPosition()) >= 5 * TICKS_RER_INCH) {
                    stage++;
                    reset_encoders();
                }
            }
            else if (stage == 6) {
                mecanum(0, 1, 0);
                // Go and face the block
                if (Math.abs(frontLeftDrive.getCurrentPosition()) >= 1 * TICKS_RER_INCH) {
                    stage++;
                    reset_encoders();
                }
            }
            else if (stage == 7) {
                myServo.setPosition(90);
                //Grab the block
                stage++;
            }
            else if (stage == 8) {
                mecanum(-1, 0, 0);
                // Go to place the first block
                if (Math.abs(frontLeftDrive.getCurrentPosition()) >= 5 * TICKS_RER_INCH) {
                    stage++;
                    reset_encoders();
                }
            }
            else if (stage == 9) {
                mecanum(0, 1, 0);
                // Repositioning
                if (Math.abs(frontLeftDrive.getCurrentPosition()) >= 1 * TICKS_RER_INCH) {
                    stage++;
                    reset_encoders();
                }
            }
            else if (stage == 10) {
                myServo.setPosition(0);
                //Place the block
                stage = 4;
            }
        }
    }

    private void all_run_using_encoders() {

    }
}