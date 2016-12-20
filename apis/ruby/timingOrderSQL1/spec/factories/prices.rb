FactoryGirl.define do
  factory :price do
    amount "9.99"
    product

    trait :without_amount do
      amount nil
    end

    trait :without_product do
      product nil
    end
  end
end
