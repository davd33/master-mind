package org.davd33.mm;

import io.vavr.collection.Queue;
import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.StringLength;

class GameImplTest {

    @Property
    @Label("Given a try input that matches exactly the secret, when trying, then 'true' is returned.")
    boolean exactMatchReturnTrue(@ForAll @StringLength(4) String input) {

        Game game = GameImpl.builder()
                .secret(input)
                .outputHistory(Queue.empty())
                .build();

        return game.newTry(input) &&
                game.dequeueOutput().equals("++++");
    }

    @Example
    @Label("Secret: 1243 , Input: 1254 Output: ++-")
    boolean ex1() {
        Game game = GameImpl.builder()
                .secret("1243")
                .outputHistory(Queue.empty())
                .build();

        return !game.newTry("1254") &&
                game.dequeueOutput().equals("++-");
    }

    @Example
    @Label("Secret: 1243 , Input: 2143 Output: ++--")
    boolean ex2() {
        Game game = GameImpl.builder()
                .secret("1243")
                .outputHistory(Queue.empty())
                .build();

        return  !game.newTry("2143") &&
                game.dequeueOutput().equals("++--");
    }

    @Example
    @Label("Secret: 7734 , Input: 1270 Output: -")
    boolean ex3() {
        Game game = GameImpl.builder()
                .secret("7734")
                .outputHistory(Queue.empty())
                .build();

        return !game.newTry("1270") &&
                game.dequeueOutput().equals("-");
    }

    @Example
    @Label("Secret: 1234 , Input: 2002 Output: -")
    boolean ex4() {
        Game game = GameImpl.builder()
                .secret("1234")
                .outputHistory(Queue.empty())
                .build();

        return !game.newTry("2002") &&
                game.dequeueOutput().equals("-");
    }

    @Example
    @Label("Secret: 1234 , Input: 2200 Output: +")
    boolean ex5() {
        Game game = GameImpl.builder()
                .secret("1234")
                .outputHistory(Queue.empty())
                .build();

        return !game.newTry("2200") &&
                game.dequeueOutput().equals("+");
    }

    @Example
    @Label("Secret: 3129 , Input: 1249 Output: +--")
    boolean ex6() {
        Game game = GameImpl.builder()
                .secret("3129")
                .outputHistory(Queue.empty())
                .build();

        return !game.newTry("1249") &&
                game.dequeueOutput().equals("+--");
    }

    @Example
    @Label("Secret: 1234 , Input: 1234 Output: ++++")
    boolean ex7() {
        Game game = GameImpl.builder()
                .secret("1234")
                .outputHistory(Queue.empty())
                .build();

        return game.newTry("1234") &&
                game.dequeueOutput().equals("++++");
    }

    @Example
    @Label("Secret: 2234 , Input: 2234 Output: ++++")
    boolean ex8() {
        Game game = GameImpl.builder()
                .secret("2234")
                .outputHistory(Queue.empty())
                .build();

        return game.newTry("2234") &&
                game.dequeueOutput().equals("++++");
    }
}