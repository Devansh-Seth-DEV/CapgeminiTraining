package com.mps.plugin;

import com.mps.contract.Command;

public class TimeCommand implements Command {
    public void execute() {
        System.out.println("Current time: " + java.time.LocalTime.now());
    }
}