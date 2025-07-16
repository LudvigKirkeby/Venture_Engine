package com.example.venture_engine.Datastructures;

import java.util.Random;
// A stats interface would probably be more optimal
abstract public class Stats {
    public int stats[];

    public int RollStrength() {
       Random rand = new Random();
      return rand.nextInt(12) + 1 + stats[1];
    }

    abstract public void Death();

}
