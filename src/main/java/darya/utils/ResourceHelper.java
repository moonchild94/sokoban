package darya.utils;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class ResourceHelper
{
    public static File getFile(String path)
    {
        URI uri;
        try
        {
            uri = ResourceHelper.class.getResource(path).toURI();
            return Paths.get(uri).toFile();
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static InputStream getStream(String path)
    {
        return ResourceHelper.class.getResourceAsStream(path);
    }
}
