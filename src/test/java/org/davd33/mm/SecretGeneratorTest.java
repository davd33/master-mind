package org.davd33.mm;

import net.jqwik.api.Label;
import net.jqwik.api.Property;

class SecretGeneratorTest {

    @Property
    @Label("A new GameImpl instance's secret is a number between 0 and 9999")
    boolean secretBoundariesForNewGame() {
        int secret = Integer.parseInt(SecretGenerator.generateSecret());
        return secret >= 0 && secret <= 9999;
    }
}