package it.unibo.gciatto.mnk.console.control.impl;

import it.unibo.gciatto.mnk.MNKMatch;
import it.unibo.gciatto.mnk.console.control.MNKConsoleControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gciatto on 02/01/17.
 */
class MNKConsoleConsoleControlImpl implements MNKConsoleControl {
    private static Pattern COORDS_PATTERN = Pattern.compile("([a-z])\\s*([a-z])");
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Optional<MNKMatch> match = Optional.empty();

    @Override
    public Optional<MNKMatch> getModel() {
        return match;
    }

    @Override
    public void setModel(MNKMatch model) {
        match = Optional.ofNullable(model);
    }

    @Override
    public void input() {
        match.ifPresent(m -> {
            final String line;
            try {
                line = reader.readLine();
                final Matcher matcher = COORDS_PATTERN.matcher(line);
                boolean read = false;
                while (!read) {
                    if (line.equals("reset")) {
                        m.reset();
                        read = true;
                    } else if (matcher.matches()) {
                        final String fst = matcher.group(1);
                        final String snd = matcher.group(2);
                        final int j = fst.charAt(0) - 'a';
                        final int i = snd.charAt(0) - 'a';
                        makeMove(i, j);
                        read = true;
                    } else {
                        System.err.println("Wrong syntax!");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void makeMove(int i, int j) {
        match.ifPresent(m -> {
            m.move(i, j);
        });
    }
}
