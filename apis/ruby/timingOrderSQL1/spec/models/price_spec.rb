require 'rails_helper'

RSpec.describe Price, type: :model do
  it 'valid info' do
    price = FactoryGirl.build(:price)

    expect(price).to be_valid
  end

  it 'invalid without amount' do
    price = FactoryGirl.build(:price, :without_amount)

    expect(price).not_to be_valid
  end

  it 'invalid without product' do
    price = FactoryGirl.build(:price, :without_product)

    expect(price).not_to be_valid
  end
end
