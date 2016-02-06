package darya.controller;

import org.eclipse.swt.widgets.Composite;

import darya.view.PlayerView;

/**
 * ���������� ������ �� �����.
 * @author ��������� �.�.
 * @sinse 5 ����. 2016 �.
 */
public class PlayerController extends SimpleController
{
    public PlayerController(Composite composite)
    {
        super(composite);
        createView();
    }

    @Override
    protected void createView()
    {
        setView(new PlayerView(getComposite()));
    }
}
