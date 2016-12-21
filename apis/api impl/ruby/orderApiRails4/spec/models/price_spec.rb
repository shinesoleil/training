require 'rails_helper'

RSpec.describe Price, type: :model do
  it 'should valid ' do
    price = FactoryGirl.build(:price)

    expect(price).to be_valid
  end

  it 'should invalid without amount' do
    price = FactoryGirl.build(:price, amount: nil)

    expect(price).not_to be_valid
  end

  it 'should invalid without product' do
    price = FactoryGirl.build(:price, product: nil)

    expect(price).not_to be_valid
  end
end
