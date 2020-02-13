package net.thumbtack.school.iface.v3;

import net.thumbtack.school.misc.v3.Human;

public interface Driveable {
    Human getDriver();

    void setDriver(Human human);

    void drive();
}
