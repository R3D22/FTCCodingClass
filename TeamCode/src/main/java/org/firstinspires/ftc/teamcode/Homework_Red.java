package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


// I'm using TeleOp instead of Autonomos
//TeleOp is the section of the robot when it is used manually
//Autonomos is used for the section of competition when the robot is working by itself

@TeleOp(name="Homework_Red", group="Iterative Opmode")
@Disabled
public class Homework_Red extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        hardwareMap.get(DistanceSensor.class, "Sensor");
        //Did as you said

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    public enum DistanceUnit
            extends java.lang.Enum<DistanceUnit>
    //When returning to the FTC coding site, it said I needed to
    //Import this code, so I did, but I don't know how to fix the new
    //Error that popped up.

    @Override
    public void loop() {

        double leftPower;
        double rightPower;

        double getDistance (DistanceUnit unit)
        static final double distanceOutOfRange;
        
        // I still don't understand why the
        // code wants a semi colon instead of quotes,
        // or why it isn't allowing the term static.

        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

    @Override
    public void stop() {
    }

}
