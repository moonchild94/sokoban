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

public class SimpleViewControllerFactory
{
    private static Map<Class<? extends GameObject>, Class<? extends AbstractController>> simpleViewControllerMap;

    static
    {
        simpleViewControllerMap = Maps.newHashMap();
        simpleViewControllerMap.put(Box.class, BoxController.class);
        simpleViewControllerMap.put(Home.class, HomeController.class);
        simpleViewControllerMap.put(Wall.class, WallController.class);
        simpleViewControllerMap.put(Player.class, PlayerController.class);
    }

    public static AbstractController getSimpleViewController(Class<? extends GameObject> simpleViewClass,
            Display display, Composite composite)
    {
        for (Map.Entry<Class<? extends GameObject>, Class<? extends AbstractController>> pair : simpleViewControllerMap
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
