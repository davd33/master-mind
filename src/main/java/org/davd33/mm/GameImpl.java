package org.davd33.mm;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Queue;
import io.vavr.collection.Vector;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;

import static io.vavr.API.*;

@Builder
@AllArgsConstructor
public class GameImpl implements Game {

    private boolean victorious = false;
    private final String secret;
    private Queue<String> outputHistory = Queue.empty();

    GameImpl() {
        secret = SecretGenerator.generateSecret();
    }

    @Override
    public boolean isVictorious() {
        return this.victorious;
    }

    @Override
    public boolean newTry(String digits) {
        if (isVictorious()) {
            return true;
        } else {

            String output = Vector.ofAll(digits.toCharArray())
                    .zipWithIndex()

                    // accumulator = Tuple < List of not tried digits , List of outputs [-+] >
                    .foldLeft(Tuple.of(Vector.of(secret.split("")), Vector.of("")), (acc, dg) -> Match(Option.of(dg._1)).of(

                            // perfect match
                            Case($(Option.of(secret.charAt(dg._2))),
                                    acc.map((r1, r2) -> acc._1.contains(dg._1.toString()) ?
                                            Tuple.of(r1.removeFirst(dg2 -> dg2.equals(dg._1.toString())), r2.append("+")) :
                                            Tuple.of(r1, r2.append("+").removeFirst(r -> r.equals("-"))))),

                            // partial match
                            Case($(Vector.ofAll(secret.toCharArray()).find(e -> e.equals(dg._1))),
                                    acc.map((r1, r2) -> acc._1.contains(dg._1.toString()) ?
                                            Tuple.of(r1.removeFirst(dg2 -> dg2.equals(dg._1.toString())), r2.append("-")) :
                                            Tuple.of(r1, r2))),

                            Case($(), acc)
                    ))
                    ._2
                    .sorted()
                    .mkString();

            outputHistory = outputHistory.enqueue(output);
            victorious = output.equalsIgnoreCase("++++");

            return victorious;
        }
    }

    @Override
    public String dequeueOutput() {
        Tuple2<String, Queue<String>> historyItem = outputHistory.dequeue();
        outputHistory = historyItem._2;
        return historyItem._1;
    }
}
