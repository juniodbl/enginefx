package br.com.engine.componentes;

import br.com.engine.core.GameObject;
import br.com.engine.interfaces.IComponent;

public abstract class SimpleComponent implements IComponent
{
    private GameObject parent;

    public GameObject getParent( )
    {
        return parent;
    }

    public void setParent( GameObject parent )
    {
        this.parent = parent;
    }
}