package br.com.engine.componentes;

import br.com.engine.componentes.drawable.Sprite;
import br.com.engine.interfaces.IComponent;

public enum TypeComponents 
{
	SPRITE(Sprite.class);
	
	private Class<? extends IComponent> value;
	
	private TypeComponents(Class<? extends IComponent> value) {
		this.value = value;
	}
	
	public Class<? extends IComponent> getValue() {
		return value;
	}
}
