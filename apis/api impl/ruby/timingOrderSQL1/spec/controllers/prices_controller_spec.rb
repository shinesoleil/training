require 'rails_helper'

RSpec.describe PricesController, type: :controller do

  it 'should list all prices of a product' do
    price = FactoryGirl.create(:price)

    get :index, params: {product_id: price.product_id}
    res = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(res.size).to eq(1)
    expect(res[0]["amount"]).to eq(price.amount.to_s)
  end

  it 'should list all prices of a product failure when nothing found' do
    product = FactoryGirl.create(:product)

    get :index, params: {product_id: product.id}

    expect(response.status).to eq(404)
  end

  it 'should find price by id' do
    price = FactoryGirl.create(:price)

    get :show, params: {product_id: price.product_id, id: price.id}

    expect(response.status).to eq(200)
  end

  it 'should find price by id failure not found' do
    product = FactoryGirl.create(:product)

    get :show, params: {product_id: product.id, id: 1}

    expect(response.status).to eq(404)
  end

  it 'should create price success' do
    product = FactoryGirl.create(:product)
    price = FactoryGirl.attributes_for(:price)

    get :create, params: {product_id: product.id, price: price}

    expect(response.status).to eq(201)
    expect(response.get_header("Location")).to match(/products\/\d\/prices\//)
  end

  it 'should create price failure without amount' do
    product = FactoryGirl.create(:product)
    price = FactoryGirl.attributes_for(:price, :without_amount)

    get :create, params: {product_id: product.id, price: price}

    expect(response.status).to eq(400)
  end
end
