FactoryGirl.define do
  factory :order_item do
    order
    product
    quantity 1
    price 9.99
    subtotal 9.99
  end
end
