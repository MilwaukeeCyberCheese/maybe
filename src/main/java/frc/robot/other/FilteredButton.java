// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.other;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Joystick;

/** Add your docs here. */
public class FilteredButton {
    private Joystick controller;

    public FilteredButton(Joystick controller) {
        this.controller = controller;
    }

    /**
     * Returns if the top left button has been pressed
     * 
     * 
     * @return boolean
     */
    public boolean getOneA() {
        return controller.getRawButton(1);
    }

    /**
     * Returns if the top middle button has been pressed
     * 
     * 
     * @return boolean
     */
    public boolean getOneB() {
        return controller.getRawButton(2);

    }

    /**
     * Returns if the top right button has been pressed
     * 
     * 
     * @return boolean
     */
    public boolean getOneC() {
        return controller.getRawButton(3);
    }

    /**
     * Returns if the middle left button has been pressed
     * 
     * 
     * @return boolean
     */
    public boolean getTwoA() {
        return controller.getRawButton(4);
    }

    /**
     * Returns if the middle middle button has been pressed
     * 
     * 
     * @return boolean
     */
    public boolean getTwoB() {
        return controller.getRawButton(5);
    }
    
    /**
     * Returns if the middle right button has been pressed
     * 
     * 
     * @return boolean
     */

    public boolean getTwoC() {
        return controller.getRawButton(6);
    }

    /**
     * Returns if the bottom left button has been pressed
     * 
     * 
     * @return boolean
     */
    public boolean getThreeA() {
        return controller.getRawButton(7);
    }

    /**
     * Returns if the bottom middle button has been pressed
     * 
     * 
     * @return boolean
     */

    public boolean getThreeB() {
        return controller.getRawButton(8);
    }

    /**
     * Returns if the bottom right button has been pressed
     * 
     * 
     * @return boolean
     */
    public boolean getThreeC() {
        return controller.getRawButton(9);
    }
}