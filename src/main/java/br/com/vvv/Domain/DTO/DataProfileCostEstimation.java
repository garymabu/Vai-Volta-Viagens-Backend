package br.com.vvv.Domain.DTO;

import java.util.List;

public record DataProfileCostEstimation(
        List<DataProfileCostEstimationEntry> individualCosts,
        Float totalCost
) {
}
