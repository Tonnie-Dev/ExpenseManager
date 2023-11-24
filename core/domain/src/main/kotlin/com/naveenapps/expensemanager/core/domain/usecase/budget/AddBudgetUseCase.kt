package com.naveenapps.expensemanager.core.domain.usecase.budget

import com.naveenapps.expensemanager.core.model.Budget
import com.naveenapps.expensemanager.core.model.Resource
import javax.inject.Inject

class AddBudgetUseCase @Inject constructor(
    private val repository: com.naveenapps.expensemanager.core.repository.BudgetRepository,
    private val checkBudgetValidateUseCase: CheckBudgetValidateUseCase
) {

    suspend operator fun invoke(budget: Budget): Resource<Boolean> {
        return when (val validationResult = checkBudgetValidateUseCase(budget)) {
            is Resource.Error -> {
                validationResult
            }

            is Resource.Success -> {
                repository.addBudget(budget)
            }
        }
    }
}