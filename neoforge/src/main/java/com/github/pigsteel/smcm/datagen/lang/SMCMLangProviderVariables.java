package com.github.pigsteel.smcm.datagen.lang;

import static com.github.pigsteel.smcm.datagen.lang.LangUtils.s;

public interface SMCMLangProviderVariables {
    String TankIllagerName();
    String EnchanterName();
    String FrozenZombieName();
    String JungleZombieName();
    String SunkenSkeletonName();
    String MossySkeletonName();
    String ParrotName();

    String FrozenZombieAmbientVerb();
    String EnchanterAmbientVerb();
    String JungleZombieAmbientVerb();
    String BruiserAmbientVerb();
    String SunkenSkeletonAmbientVerb();
    String MossySkeletonAmbientVerb();

    String DiesVerb();
    String HurtsVerb();
}
