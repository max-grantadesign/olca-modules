package org.openlca.core.database.references;

import java.util.List;
import java.util.Set;

import org.openlca.core.database.IDatabase;
import org.openlca.core.database.references.Search.Ref;
import org.openlca.core.model.Category;
import org.openlca.core.model.UnitGroup;
import org.openlca.core.model.descriptors.FlowPropertyDescriptor;

public class FlowPropertyReferenceSearch extends
		BaseReferenceSearch<FlowPropertyDescriptor> {

	private final static Ref[] references = { 
		new Ref(Category.class, "f_category", true),
		new Ref(UnitGroup.class, "f_unit_group") 
	};

	public FlowPropertyReferenceSearch(IDatabase database, boolean includeOptional) {
		super(database, includeOptional);
	}

	@Override
	public List<Reference> findReferences(Set<Long> ids) {
		return findReferences("tbl_flow_properties", "id", ids, references);
	}

}
