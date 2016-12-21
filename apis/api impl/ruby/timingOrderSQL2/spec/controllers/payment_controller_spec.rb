require 'rails_helper'

RSpec.describe PaymentController, type: :controller do
  it "should get payment of order" do
    @order = FactoryGirl.create(:order)
    @payment = FactoryGirl.create(:payment, order: @order)

    get :index, params: {customer_id: @order.customer_id, order_id: @order.id}
    @payment_json = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(@payment_json["amount"]).to eq(@payment.amount.to_s)
  end

  it "should get payment of order failure when not found" do
    @order = FactoryGirl.create(:order)

    get :index, params: {customer_id: @order.customer_id, order_id: @order.id}

    expect(response.status).to eq(404)
  end

  it "should create payment success" do
    @order = FactoryGirl.create(:order)
    @payment = FactoryGirl.attributes_for(:payment)

    post :create, params: {customer_id: @order.customer_id, order_id: @order.id, payment: @payment}
    @payment_json = JSON.parse(response.body)

    expect(response.status).to eq(201)
    expect(@payment_json["amount"]).to eq(@payment[:amount].to_s)
  end

  it "should create payment failure without amount" do
    @order = FactoryGirl.create(:order)
    @payment = FactoryGirl.attributes_for(:payment, :without_amount)

    post :create, params: {customer_id: @order.customer_id, order_id: @order.id, payment: @payment}

    expect(response.status).to eq(400)
  end
end
