package de.id.platzi.base;

import de.id.platzi.config.Config;
import de.id.platzi.config.ConfigLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    protected Config config;

    @BeforeAll
    void loadConfig() {
        this.config = ConfigLoader.load();
    }
}
