package net.thumbtack.school.iface.v2;

import net.thumbtack.school.misc.v2.Human;

public interface Driveable {
    void setDriver(Human human);
    Human getDriver();
    void drive();
}
