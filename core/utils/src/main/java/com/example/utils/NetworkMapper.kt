package com.example.utils

interface NetworkMapper<NetworkModel, ExternalModel> {
    fun mapFromNetwork(networkModel: NetworkModel) : ExternalModel
}