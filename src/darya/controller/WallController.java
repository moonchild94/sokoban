package src.darya.controller;

import org.eclipse.swt.widgets.Composite;

import src.darya.view.WallView;

/**
 * ���������� �������� ������� �����.
 * @author ��������� �.�.
 * @sinse 5 ����. 2016 �.
 */
public class WallController extends SimpleController
{
    public WallController(Composite composite)
    {
        super(composite);
        createView();
    }

    @Override
    protected void createView()
    {
        setView(new WallView(getComposite()));
    }
}