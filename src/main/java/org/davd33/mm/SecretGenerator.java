package org.davd33.mm;

import io.vavr.collection.Vector;

public class SecretGenerator {

    public static String generateSecret() {
        return Vector.of('1', '1', '1', '1')
                .map(c -> Vector.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
                        .get(((Double) (Math.random() * 10)).intValue()))
                .mkString()
                .substring(0, 4);
    }
}
