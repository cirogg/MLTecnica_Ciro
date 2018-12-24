package com.example.ciro_.mltecnica.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cuotas {

    @SerializedName("payment_method_id")
    @Expose
    private String paymentMethodId;
    @SerializedName("payment_type_id")
    @Expose
    private String paymentTypeId;
    @SerializedName("processing_mode")
    @Expose
    private String processingMode;
    @SerializedName("payer_costs")
    @Expose
    private List<PayerCost> payerCosts = null;

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getProcessingMode() {
        return processingMode;
    }

    public void setProcessingMode(String processingMode) {
        this.processingMode = processingMode;
    }

    public List<PayerCost> getPayerCosts() {
        return payerCosts;
    }

    public void setPayerCosts(List<PayerCost> payerCosts) {
        this.payerCosts = payerCosts;
    }

    public Cuotas(String paymentMethodId, String paymentTypeId, String processingMode, List<PayerCost> payerCosts) {
        this.paymentMethodId = paymentMethodId;
        this.paymentTypeId = paymentTypeId;
        this.processingMode = processingMode;
        this.payerCosts = payerCosts;
    }

    public Cuotas() {
    }


}
