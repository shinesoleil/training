require 'rails_helper'

RSpec.describe OrdersController, type: :controller do
  it 'should list all orders' do
    product = FactoryGirl.create(:product)
    customer = FactoryGirl.create(:customer)
    order = FactoryGirl.create(:order, customer: customer)
    order_item = FactoryGirl.create(:order_item, order: order, product: product)

    get :index, params: {customer_id: customer.id}
    res = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(res.size).to eq(1)
  end

  it 'should list all orders failure when nothing found' do
    product = FactoryGirl.create(:product)
    customer = FactoryGirl.create(:customer)

    get :index, params: {customer_id: customer.id}

    expect(response.status).to eq(404)
  end

  it 'should find by id success' do
    product = FactoryGirl.create(:product)
    customer = FactoryGirl.create(:customer)
    order = FactoryGirl.create(:order, customer: customer)
    order_item = FactoryGirl.create(:order_item, order: order, product: product)

    get :show, params: {customer_id: customer.id, id: order.id}

    expect(response.status).to eq(200)
  end

  it 'should find by id success' do
    product = FactoryGirl.create(:product)
    customer = FactoryGirl.create(:customer)

    get :show, params: {customer_id: customer.id, id: 1}

    expect(response.status).to eq(404)
  end

  it 'should create order success' do
    product = FactoryGirl.create(:product)
    customer = FactoryGirl.create(:customer)
    order_item = FactoryGirl.attributes_for(:order_item)
    order_item[:product_id] = product.id

    post :create, params: {customer_id: customer.id, order_items: [order_item]}
    res = JSON.parse(response.body)

    expect(response.status).to eq(201)
  end

end
