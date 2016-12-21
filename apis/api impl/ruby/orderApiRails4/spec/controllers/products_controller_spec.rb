require 'rails_helper'

RSpec.describe ProductsController, type: :controller do
  it 'get all products' do
    price = FactoryGirl.create(:price)
    get :index
    res = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(res.size).to eq(1)
  end

  it 'get all products fails when not found' do
    get :index

    expect(response.status).to eq(404)
  end

  it 'get product by id' do
    price = FactoryGirl.create(:price)
    get :show, params: {id: price.id}
    res = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(res["id"]).to eq(1)
  end

  it 'get product by id fail when not found' do
    get :show, params: {id: 1}

    expect(response.status).to eq(404)
  end

  it 'create product success' do
    post :create, params: {product: FactoryGirl.attributes_for(:product)}

    expect(response.status).to eq(201)
    expect(response.get_header("Location")).to match(/products\/\d/)
  end

  it 'create product fails without valid info' do
    info = FactoryGirl.attributes_for(:product)
    info[:name] = nil
    post :create, params: {product: info}

    expect(response.status).to eq(400)
  end
end
