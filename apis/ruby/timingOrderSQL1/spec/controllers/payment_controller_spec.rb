require 'rails_helper'

RSpec.describe PaymentController, type: :controller do
  it 'should get payment of order' do
    customer = FactoryGirl.create(:customer)
    order = FactoryGirl.create(:order, customer: customer)
    payment = FactoryGirl.create(:payment, order: order)

    get :index, params: {customer_id: customer.id, order_id: order.id}
    res = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(res["order_id"]).to eq(order.id)
    expect(res["amount"]).to eq(payment.amount.to_s)
  end

  it 'should get payment fails when nothing found' do
    customer = FactoryGirl.create(:customer)
    order = FactoryGirl.create(:order, customer: customer)

    get :index, params: {customer_id: customer.id, order_id: order.id}

    expect(response.status).to eq(404)
  end

  it 'should create payment success' do
    customer = FactoryGirl.create(:customer)
    order = FactoryGirl.create(:order, customer: customer)
    payment = FactoryGirl.attributes_for(:payment)

    post :create, params: {customer_id: customer.id, order_id: order.id, payment: payment}

    expect(response.status).to eq(201)
    expect(response.get_header("Location")).to match(/customers\/\d\/orders\/\d\/payment/)
  end

  it 'should create payment failure without amount' do
    customer = FactoryGirl.create(:customer)
    order = FactoryGirl.create(:order, customer: customer)
    payment = FactoryGirl.attributes_for(:payment, :without_amount)

    post :create, params: {customer_id: customer.id, order_id: order.id, payment: payment}

    expect(response.status).to eq(400)
  end
end
