/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.launch.platform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class CommandLineOptions {
    private List<String> configs = new ArrayList<String>();

    private CommandLineOptions() {
    }

    public List<String> getConfigs() {
        return Collections.unmodifiableList(this.configs);
    }

    private void parseArgs(List<String> args2) {
        boolean captureNext = false;
        for (String arg : args2) {
            if (captureNext) {
                this.configs.add(arg);
            }
            captureNext = "--mixin".equals(arg) || "--mixin.config".equals(arg);
        }
    }

    public static CommandLineOptions defaultArgs() {
        return CommandLineOptions.ofArgs(null);
    }

    public static CommandLineOptions ofArgs(List<String> args2) {
        String argv;
        CommandLineOptions options = new CommandLineOptions();
        if (args2 == null && (argv = System.getProperty("sun.java.command")) != null) {
            args2 = Arrays.asList(argv.split(" "));
        }
        if (args2 != null) {
            options.parseArgs(args2);
        }
        return options;
    }

    public static CommandLineOptions of(List<String> configs) {
        CommandLineOptions options = new CommandLineOptions();
        options.configs.addAll(configs);
        return options;
    }
}

