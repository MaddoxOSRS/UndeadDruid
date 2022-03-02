package io.maddox.behaviour.fallback;


import io.maddox.data.Configs;
import io.maddox.data.Constants;
import io.maddox.framework.Leaf;

public class FallbackLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int onLoop() {
        return Constants.loopReturn;
    }
}
