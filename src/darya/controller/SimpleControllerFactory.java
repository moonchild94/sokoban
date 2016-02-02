package src.darya.controller;

import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Maps;

import src.darya.model.Box;
import src.darya.model.GameObject;
import src.darya.model.Home;
import src.darya.model.Player;
import src.darya.model.Wall;

public class SimpleControllerFactory
{
    private static Map<Class<? extends GameObject>, Class<? extends SimpleController>> simpleControllerMap;

    static
    {
        simpleControllerMap = Maps.newHashMap();
        simpleControllerMap.put(Box.class, BoxController.class);
        simpleControllerMap.put(Home.class, HomeController.class);
        simpleControllerMap.put(Wall.class, WallController.class);
        simpleControllerMap.put(Player.class, PlayerController.class);
    }

    public static SimpleController getSimpleViewController(Class<? extends GameObject> simpleViewClass, Display display,
            Composite composite)
    {
        for (Map.Entry<Class<? extends GameObject>, Class<? extends SimpleController>> pair : simpleControllerMap
                .entrySet())
        {
            if (pair.getKey().equals(simpleViewClass))
            {
                try
                {
                    return pair.getValue().getConstructor(Display.class, Composite.class).newInstance(display,
                            composite);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }

        return null;
    }
}
