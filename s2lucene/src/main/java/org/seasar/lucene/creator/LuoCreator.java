package org.seasar.lucene.creator;

import org.seasar.framework.container.ComponentCustomizer;
import org.seasar.framework.container.creator.ComponentCreatorImpl;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.convention.NamingConvention;

public class LuoCreator extends ComponentCreatorImpl {

	public LuoCreator(NamingConvention namingConvention) {
		super(namingConvention);
		setNameSuffix("Luo");
		setInstanceDef(InstanceDefFactory.PROTOTYPE);
		setEnableInterface(true);
		setEnableAbstract(true);
	}

	public ComponentCustomizer getLuoCustomizer() {
		return getCustomizer();
	}

	public void setLuoCustomizer(ComponentCustomizer customizer) {
		setCustomizer(customizer);
	}

}
