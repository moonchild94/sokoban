package darya.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * ����������� ����� ������ � xml.
 * @author ��������� �.�.
 * @sinse 5 ����. 2016 �.
 */
public class XMLHelper
{
    public static Object unwrapFromXML(File file, Class<?> clazz)
    {
        try
        {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Object o = unmarshaller.unmarshal(file);
            return o;
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void wrapToXML(File file, Object o)
    {
        try
        {
            JAXBContext context = JAXBContext.newInstance(o.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(o, file);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }
}