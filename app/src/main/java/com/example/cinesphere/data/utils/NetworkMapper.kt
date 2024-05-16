package com.example.cinesphere.data.utils

interface NetworkMapper<NetworkModel, ExternalModel> {
    fun mapFromNetwork(networkModel: NetworkModel) : ExternalModel
}