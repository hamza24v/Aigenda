import React, { useState, useEffect } from 'react'

export const Form = ({ fields, onSubmit, submitText, defaultValues={} }) => {
    
    const [form, setForm] = useState(defaultValues);

    // useEffect(() => {
    //   setForm(defaultValues);
    //   console.log("default values: ");
    //   console.log(defaultValues);
    // }, [defaultValues]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm({
          ...form,
          [name]: value,
        });
      };
      const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(form);
      }
  return (
    <form onSubmit={handleSubmit} className="space-y-6">
      {fields.map((field) => (
        <div key={field.name} className="form-group">
          <label className="block font-medium text-gray-700">
            {field.label}
          </label>
          {field.type === 'select' ? (
            <select
              name={field.name}
              value={form[field.name]}
              onChange={handleChange}
              className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            >
              <option value="">Select {field.label}</option>
              {field.options.map((option, index) => (
                <option key={index} value={option.value}>
                  {option.label}
                </option>
              ))}
            </select>
          ) : (
            <input
              type={field.type || 'text'}
              name={field.name}
              value={form[field.name]}
              onChange={handleChange}
              placeholder={field.placeholder}
              className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            />
          )}
        </div>
      ))}
      <button
        type="submit"
        className="bg-indigo-600 text-white py-2 px-4 w-full mx-auto text-center rounded-md hover:bg-indigo-700 transition duration-200"
        on
      >
        {submitText}
      </button>
    </form>
  )
}