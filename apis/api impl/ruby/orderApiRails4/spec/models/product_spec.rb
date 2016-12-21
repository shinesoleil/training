require 'rails_helper'

RSpec.describe Product, type: :model do
  it 'should valid ' do
    product = FactoryGirl.build(:product)

    expect(product).to be_valid
  end

  it 'should invalid without name' do
    product = FactoryGirl.build(:product, name: nil)

    expect(product).not_to be_valid
  end

end
