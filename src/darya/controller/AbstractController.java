package src.darya.controller;

import org.eclipse.swt.widgets.Composite;

import src.darya.view.View;

/**
 * ����������� �����, ���������� ������� ������ ������
 * ������������ � ����.
 * @author ��������� �.�.
 * @sinse 4 ����. 2016 �.
 */
public abstract class AbstractController implements IController
{
    private Composite composite;
    private View view;

    public AbstractController(Composite composite)
    {
        this.composite = composite;
    }

    public Composite getComposite()
    {
        return composite;
    }

    public View getView()
    {
        return view;
    }

    public void setView(View view)
    {
        this.view = view;
    }

    protected abstract void createView();
}
