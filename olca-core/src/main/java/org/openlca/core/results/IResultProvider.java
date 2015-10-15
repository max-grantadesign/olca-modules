package org.openlca.core.results;

import java.util.Set;

import org.openlca.core.model.descriptors.CostCategoryDescriptor;
import org.openlca.core.model.descriptors.FlowDescriptor;
import org.openlca.core.model.descriptors.ImpactCategoryDescriptor;
import org.openlca.core.model.descriptors.ProcessDescriptor;

/**
 * General methods for LCA result providers.
 */
public interface IResultProvider {

	/**
	 * Indicates whether the result has an LCIA result or not.
	 */
	boolean hasImpactResults();

	/**
	 * Indicates whether the result has a LCC result or not.
	 */
	boolean hasCostResults();

	/**
	 * Get the descriptors of all processes in the result.
	 */
	Set<ProcessDescriptor> getProcessDescriptors();

	/**
	 * Get the descriptors of all flows in the result.
	 */
	Set<FlowDescriptor> getFlowDescriptors();

	/**
	 * Get the descriptors of all LCIA categories in the result.
	 */
	Set<ImpactCategoryDescriptor> getImpactDescriptors();

	/**
	 * Get the descriptors of all LCC categories in the result.
	 */
	Set<CostCategoryDescriptor> getCostDescriptors();
}
