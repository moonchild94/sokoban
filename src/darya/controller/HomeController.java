package src.darya.controller;

import org.eclipse.swt.widgets.Composite;

import src.darya.view.HomeView;

/**
 * ���������� ������ ����.
 * @author ��������� �.�.
 * @sinse 4 ����. 2016 �.
 */
public class HomeController extends SimpleController
{
    public HomeController(Composite composite)
    {
        super(composite);
        createView();
    }

    @Override
    protected void createView()
    {
        setView(new HomeView(getComposite()));
    }
}
