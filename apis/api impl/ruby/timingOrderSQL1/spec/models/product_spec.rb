require 'rails_helper'

RSpec.describe Product, type: :model do
  it 'valid info' do
    product = FactoryGirl.build(:product)

    expect(product).to be_valid
  end

  it 'invalid without name' do
    product = FactoryGirl.build(:product, :without_name)

    expect(product).not_to be_valid
  end
end
