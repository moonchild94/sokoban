package darya.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Утилитарный класс работы с файлами.
 * @author Калмыкова Д.В.
 * @sinse 6 февр. 2016 г.
 */
public class ResourceHelper
{
    public static File getBinaryFile(String path)
    {
        File file = null;

        try
        {
            InputStream input = ResourceHelper.class.getResourceAsStream(path);
            file = File.createTempFile("tempfile", ".tmp");
            try (OutputStream out = new FileOutputStream(file))
            {
                int read;
                byte[] bytes = new byte[1024];

                while ((read = input.read(bytes)) != -1)
                {
                    out.write(bytes, 0, read);
                }
                file.deleteOnExit();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return file;
    }

    public static InputStream getStream(String path)
    {
        return ResourceHelper.class.getResourceAsStream(path);
    }
}
