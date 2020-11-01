package com.example.dmytroberezhnyi_vdatatesttask.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.dmytroberezhnyi_vdatatesttask.data.repository.CompanyRepository

class CollaboratorViewModel @ViewModelInject constructor(
    val repository: CompanyRepository
) : ViewModel() {

    val collaboratorsWithCompanies = repository.getCollaboratorsWithCompanies()
}