package net.thumbtack.school.iface.v2;

import net.thumbtack.school.misc.v2.Human;

public interface Driveable {
    Human getDriver();

    void setDriver(Human human);

    void drive();
}
