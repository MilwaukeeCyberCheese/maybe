// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.other;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Joystick;

/** Add your docs here. */
public class FilteredButtons {
    private Joystick controller;

    public FilteredButtons(Joystick controller) {
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

}