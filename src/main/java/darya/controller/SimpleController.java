package darya.controller;

import org.eclipse.swt.widgets.Composite;

import darya.view.SimpleView;

/**
 * ����������� ����� �����������, ����������� �����������.
 * @author ��������� �.�.
 * @sinse 5 ����. 2016 �.
 */
public abstract class SimpleController extends AbstractController
{
    protected SimpleView view;

    public SimpleController(Composite composite)
    {
        super(composite);
    }

    @Override
    public SimpleView getView()
    {
        return view;
    }

    public void setView(SimpleView view)
    {
        this.view = view;
    }
}