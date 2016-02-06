package darya.controller;

import org.eclipse.swt.widgets.Composite;

import darya.view.BoxView;

/**
 * ���������� �����
 * @author ��������� �.�.
 * @sinse 4 ����. 2016 �.
 */
public class BoxController extends SimpleController
{
    public BoxController(Composite composite)
    {
        super(composite);
        createView();
    }

    @Override
    protected void createView()
    {
        setView(new BoxView(getComposite()));
    }
}
