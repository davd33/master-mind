package org.davd33.mm;

public interface Game {

    boolean isVictorious();

    boolean newTry(String digits);

    String dequeueOutput();
}
